from genericpath import exists
import sys
import smtplib
from urllib import response

from flask import Flask, render_template, json, request, make_response, \
    session, send_file, send_from_directory, jsonify, redirect, url_for, redirect
from flask import Blueprint
from domains.User import User
from services.CatalogueService import CatalogueService

import ast


user_page = Blueprint('user_page', __name__, template_folder='/static')

root = '/api/users'

service = CatalogueService()


@user_page.route(root, methods=['POST'])
def store():
    json = request.get_json()
    #print(json)
    doc = User(**json)
    #print("aqui", doc)
    q = service.add(doc)
    return jsonify(q.as_dict())

@user_page.route(root+'/sign', methods=['POST'])
def login():
    json = request.get_json()
    doc = User(**json)
    q = doc.sign(json['password'])
    # return {"token": q}
    return q

# Get list
@user_page.route(root, methods=['GET'])
def get():
    params = request.args.to_dict()
    objs = service.list('User', params)
    return jsonify(cursor=objs["cursor"], entities = [o.as_dict() for o in objs["entities"]])

#Get single
@user_page.route(root+'/<id>', methods=['GET'])
def get_first(id):
    exist = User(id)
    obj = service.single(exist, True)
    return jsonify(obj.as_dict())

#Update
@user_page.route(root+'/<id>', methods=['PUT'])
def update(id):
    json = request.get_json()
    doc = User(**json)
    q = service.update(id, doc, True)
    return jsonify(q.as_dict())
