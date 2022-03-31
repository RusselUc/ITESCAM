from grpc import Status
from domains.base.Model import Model
import sys
from firebase_admin import firestore
import datetime
import traceback
from pytz import timezone

from werkzeug.security import generate_password_hash, check_password_hash

import json
import ast

class User(Model):
    def __init__(self, id = '', email = '', password = '', profile = 0, name = '', gender = '', surname = '', second_surname = '' ,address = '', createdOn = '', updatedOn = ''):
        Model.__init__(self,id)
        self.email = email
        if not password == '':
            self.password = generate_password_hash(password)
        if id == '' and password == '' :
            self.password = generate_password_hash(password)
        self.profile = profile
        self.name = name
        self.surname = surname
        self.second_surname = second_surname
        self.gender = gender
        self.address = address
        self.gender = gender
        pass

    def user_exist(self):
        db = firestore.client()
        user_ref = db.collection(type(User).__name__)

        try:
            query = (
                user_ref.where('email', u'==', self.email)
                    .where('password', u'==', self.password)
                    .order_by('name')
            )
            docs = query.stream()
            r = [doc.to_dict() for doc in docs]
            if len(r) > 0:
                return 2
            else:
                return 0
        except Exception:
            print(u'Error', )
            print(traceback.print_exc(), file=sys.stderr)
            return -1

    def exist(self, email):
        db = firestore.client()
        user_ref = db.collection('User')
        query = user_ref.where('email', '==', self.email).get()
        if query:
            return {"message": "El correo ya se encuentra registrado"}

    def sign(self, password):
        db = firestore.client()
        user_ref = db.collection('User')
        query = user_ref.where('email', '==', self.email).get()

        if query:
            for i in query:
                databd = i.to_dict()
            if check_password_hash(databd['password'], password):
                status = dict(databd)
                status['password'] = password
                data =  write_token(databd)
                responseToken = data.decode("utf-8")
            else:
                responseToken = "Wrong Password"
                status = ''
            return {"user": status, "token": responseToken}
        else:
            return {"user":"", "token":"User Not Found"}

    def updateStatus(self):
        db = firestore.client()
        user_ref = db.collection('User')
        query = user_ref.where('email', '==', self.email).get()

        if query:
            for i in query:
                databd = i.to_dict()

            dataU = {"status":True}
            user_ref.document(databd['id']).update(dataU)
            return {"message":"exitoso"}
