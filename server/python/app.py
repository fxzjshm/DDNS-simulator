# coding: utf-8

import sys, traceback

from datetime import datetime

from flask import Flask
from flask import render_template
from flask import redirect

import leancloud
from leancloud import Query
from leancloud import Object

IP_User_Obj = Object.extend('IP_User_Obj')

app = Flask(__name__)
query = Query(IP_User_Obj)
leancloud.client.use_region('CN')
leancloud.init('CaiXYnmy3BPpcpVE9eRaKQkV-gzGzoHsz','7tryYPnSzMGwpivDanO2yfG0')

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/<objectId>')
def redirectWithObjectId(objectId):
    try:
        ip = getIP(objectId)
        return redirect('http://' + ip + "/");
        #return render_template('redirecting.html');
    except Exception, e:
        print e
        print traceback.format_exc()
        #print td
        return render_template('index.html', exception = e)

def getIP(objectId):
    if len(objectId) is 24 :
        #leancloud.init('CaiXYnmy3BPpcpVE9eRaKQkV-gzGzoHsz','7tryYPnSzMGwpivDanO2yfG0')
        #print objectId
        #print IP_User_Obj._class_name
        #object = query.get(objectId)
        ipObject = IP_User_Obj.create_without_data(objectId)
        #ipObject.save()
        ipObject.fetch()
        #if object is None :
        #    return render_template('index.html')
        ip = ipObject.get('IP')
        if ip is None :
            raise ValueError, 'Cannot get ip. ' # 'Please check if this object id has been already used.'
        #print ip
    else:
        raise ValueError, 'Invalid object id.'
