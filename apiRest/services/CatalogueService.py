from abc import ABC, abstractmethod
from firebase_admin import credentials
from firebase_admin import firestore
from collections import namedtuple
from services.FactoryT import FactoryT

import logging
import traceback
import re


import connection

class CatalogueService:
    rangeRgx = "(\\*?)([A-Z_a-z0-9]+)\\^(.*)~(.*)"
    whereInRgx = "([A-Z_a-z0-9]+)\\((.+(,.+)*)\\)"

    def get_db_client(self):
        """ Devuelve un objeto que contiene la conexión a Firestore
        Retorna:
        client - Referencia a una conexión establecida con Firestore
        """
        client = firestore.client()
        return client

    def add(self, obj, relations = False):
        kind = type(obj).__name__

        #print(kind)
        db = self.get_db_client()
        ref = db.collection(kind).document()
        obj.id = str(ref.id)
        obj.newTimestamps()
        #print(obj.as_dict())
        ref.set(obj.as_dict())
        # if relations:
        #     obj.makeRelationships()
        return obj

    def single(self, objWithId, relations = False):
        kind = type(objWithId).__name__
        db = self.get_db_client()
        factory = FactoryT()
        newObj = factory.create(kind)
        # print(objWithId.id)
        if objWithId.id:
            ref = db.collection(kind).document(objWithId.id)
            data = ref.get()
            newObj.from_dict(data.to_dict())
            # if relations:
            #     newObj.makeRelationships()
            return newObj
        return None

    def update(self, id, obj, relations = False):
        kind = type(obj).__name__

        db = self.get_db_client()
        existing = self.single(obj)
        if existing is None:
            print('[{}] None of {}'.format(id, obj.id))
            return existing
        obj.setUpdatedOn()
        updateRef = db.collection(kind).document(id)
        print('Objeto actualizado:')
        print(obj.as_dict())
        updateRef.set(obj.as_dict(), merge= True)
        print('Objeto después de actualización')
        print(obj.as_dict())
        # if relations:
        #     obj.makeRelationships()
        return obj
    
    def delete(self, objWithId):
        kind = type(objWithId).__name__
        existing = self.single(objWithId)
        db = self.get_db_client()
        if existing == None:
            return existing
        deleteRef = db.collection(kind).document(objWithId.id)
        deleteRef.delete()
        return existing
    
    def list(self, clazz: str, filters = {}, cursor = None):
        kind = clazz
        factory = FactoryT()
        db = self.get_db_client()
        listWCursor = {
            "cursor": None,
            "entities": []
        }
        # print("kind:" + kind)
        # print("filters:")
        # print(filters)
        cref = db.collection(kind)

        limit = filters.get('limit')
        filters.pop('limit', None)
        rel = filters.get('rel')
        filters.pop('rel', None)
        _range = filters.get('range')
        filters.pop('range', None)
        whereIn = filters.get('in')
        filters.pop('in', None)
        orderby = filters.get('orderBy')
        filters.pop('orderBy', None)

        max_value = 0
        if limit == None:
            max_value = 1000
        else:
            max_value = int(limit)
        query = cref.limit(max_value)

        if orderby is not None:
            tagOrder = orderby[0]
            field = orderby
            direction = firestore.firestore.Query.ASCENDING
            if tagOrder == '~':
                field = orderby[1:]
            elif tagOrder == '-':
                field = orderby[1:]
                direction = firestore.firestore.Query.DESCENDING
            query = query.order_by(field, direction)
            #print("orderBy : " + field + " | " + direction)
        if _range is not None:
            pattern = re.compile(self.rangeRgx)
            matcher = pattern.match(_range)
            asterik = matcher.group(1)
            field = matcher.group(2)
            start = matcher.group(3)
            end = matcher.group(4)

            if asterik is None:
                if query is None:
                    query = cref.where(field, '>=', start).where(field, '<=', end)
                else:
                    query = query.where(field, '>=', start).where(field, '<=', end)
                rangeFilter = str.format("({} >= {} AND {} <= {})", field, start, field, end)
            else:
                if query is None:
                    query = cref.where(field, '>', start).where(field, '<', end)
                else:
                    query = query.where(field, '>', start).where(field, '<', end)
                rangeFilter = str.format("({} > {} AND {} < {})", field, start, field, end)
            #print(rangeFilter)
        if whereIn is not None:
            pattern = re.compile(self.whereInRgx)
            matcher = pattern.match(whereIn)
            field = matcher.group(1)
            values = matcher.group(2)
            #print(field + u'array_contains_any' + str(values))
            #print(values.split(","))
            query = query.where(field, u'array_contains_any', str(values).split(","))
        for filter in filters:
            field = filter
            value = filters[filter]
            #print('['+ field+']='+value)
            query = query.where(field, u'==', value)
        if cursor is not None:
            try:
                snap = db.collection(kind).document(cursor).get()
                if query is not None:
                    query = query.start_at(snap)
            except Exception:
                traceback.print_exc()
                return None
            else:
                return None
        docs = None
        if query is None:
            docs = cref.stream()
        else:
            docs = query.stream()
        for doc in docs:
            obj = factory.create(kind)
            #print(doc.to_dict())
            factory.populate(obj, doc.to_dict())
            #print(obj.createdOn)
            listWCursor["entities"].append(obj)
        if len(listWCursor["entities"]) == max_value:
            listWCursor['cursor'] = listWCursor["entities"][-1].id
        return listWCursor