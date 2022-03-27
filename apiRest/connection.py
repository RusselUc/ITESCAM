import firebase_admin
from firebase_admin import credentials, firestore

cred = credentials.Certificate("key.json")

app = firebase_admin.initialize_app(cred)

database = firestore.client()

if __name__ == "__main__":
    print("successful connection")