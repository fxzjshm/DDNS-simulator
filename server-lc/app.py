# coding: utf-8

from datetime import datetime

from flask import Flask
from flask import render_template
from flask import redirect

import leancloud
from leancloud import Query
from leancloud import Object

IP_User_Obj = Object.extend('IP_User_Obj')

app = Flask(__name__)
leancloud.init('CaiXYnmy3BPpcpVE9eRaKQkV-gzGzoHsz','7tryYPnSzMGwpivDanO2yfG0')
query = Query(IP_User_Obj)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/<objectId>')
def redirectWithObjectId(objectId):
    if len(objectId) is 24 :
        #print objectId
        #print IP_User_Obj._class_name
        #object = query.get(objectId)
        object = IP_User_Obj.create_without_data(objectId)
        #object.save()
        object.fetch()
        #if object is None :
        #    return render_template('index.html')
        ip = object.get('IP')
        if ip is None :
            return render_template('index.html')
        #print ip
        return redirect('http://' + ip + "/");
        #return render_template('redirecting.html');
    else:
        return render_template('index.html')
