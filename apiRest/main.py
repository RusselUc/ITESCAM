from distutils.log import debug
from flask import Flask
from dotenv import load_dotenv
from controllers.UserController import user_page

from flask_cors import CORS


app = Flask(__name__)

app.register_blueprint(user_page)


CORS(app)


if __name__ == '__main__':
    load_dotenv
    app.run(debug=True, port="4000", host="0.0.0.0")