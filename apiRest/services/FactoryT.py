import abc
from abc import ABC
from domains.User import User

class FactoryT(ABC):

    def create(self, kind):
        obj = None
        if kind == 'User':
            return User()

    def populate(self, obj, data):
        kind = type(obj).__name__
        if kind in ['User']:
            obj.from_dict(data)
