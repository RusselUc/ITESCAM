from abc import ABC
import abc
from datetime import datetime
from pytz import timezone

class Model(ABC):
    id = ''
    createdOn = ''
    updatedOn = ''

    def __init__(self, id = ''):
        self.id = id
        self.createdOn = datetime.today().astimezone(
            timezone('America/Mexico_City')).strftime("%d/%m/%Y, %H:%M")
        self.updatedOn = datetime.today().astimezone(
            timezone('America/Mexico_City')).strftime("%d/%m/%Y, %H:%M")
        pass

    def as_dict(self):
        o = self.__dict__
        return o
    def from_dict(self, to):
        for field in self.__dict__:
            if to is not None:
                if to.get(field):
                    self.__setattr__(field, to[field])
        pass

    def newTimestamps(self):
        self.createdOn = datetime.today().astimezone(
            timezone('America/Mexico_City')).strftime("%d/%m/%Y, %H:%M")
        self.updatedOn = datetime.today().astimezone(
            timezone('America/Mexico_City')).strftime("%d/%m/%Y, %H:%M")
        pass
    def setUpdatedOn(self):
        self.updatedOn = datetime.today().astimezone(
            timezone('America/Mexico_City')).strftime("%d/%m/%Y, %H:%M")
        pass