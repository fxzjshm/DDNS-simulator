#!/usr/bin/python2.7
import sys
from sys import argv
import leancloud
from leancloud import Query
from leancloud import Object
import httplib
from httplib import HTTPConnection
import socket

IP_User_Obj = Object.extend('IP_User_Obj')

leancloud.client.use_region('CN')
leancloud.init('CaiXYnmy3BPpcpVE9eRaKQkV-gzGzoHsz','7tryYPnSzMGwpivDanO2yfG0')

def get(url, port, fileName):
    if(url == None or url == ''  or fileName == None or fileName == ''):
        raise Exception('Illegal url.')
    # client = None
    try:
        client = HTTPConnection(url, port)
        if fileName.find('/') == 1:
            client.request('GET', fileName)
        else:
            client.request('GET', '/' + fileName)
        response = client.getresponse()
        return response.read()
    except Exception, e:
        print e
    finally:
        if client:
            client.close()

def getMyIP():
    response = get('1212.ip138.com', 80, 'ic.asp')
    # print response
    start = response.index('[')
    end = response.index(']')
    ip = response[start + 1 : end]
    return ip

# def main():
'''WARNING: Wrong logic reading args. '''
# if len(argv) == 1 or argv[1] == None or argv[1] == '' :
#     print 'ObjectId cannot be None.'
#     sys.exit(1)
# if argv[1] is 'create' or argv[1] is 'Create':
#     doesCreate = True
#     ipObject=IP_User_Obj()
# elif len(argv[1]) is 24:
#     objectId=argv[1]
#     ipObject=IP_User_Obj.create_without_data(objectId)
    # Just dor debug.
    # print objectId
    # print argv[1]
# else:
#     raise ValueError, 'Invalid object id.'
ipObject = None
doesCheck = False
if len(sys.argv) is 1:
    raise ValueError, 'Sorry but you\'ve typed in invalid object id.'
    sys.exit(1)
for arg in sys.argv:
    if arg == 'create' or arg == 'Create' or arg == 'CREATE':
        ipObject=IP_User_Obj()
    if arg == '-c' or arg == '--check':
        doesCheck = True
    if len(arg) == 24:
       objectId=arg
       ipObject=IP_User_Obj.create_without_data(objectId)
if ipObject is None:
    raise ValueError, 'Sorry but you\'ve typed in invalid object id.'
    sys.exit(1)

ip=getMyIP()
print 'Public IP : ' + ip

ipObject.set('IP', ip)
ipObject.set('HostName', socket.gethostname())
ipObject.save()
print ipObject.dump()

if doesCheck == True:
    print 'Now fetching data from server. '
    print 'Please check it by yourself. (Ignore that small \'u\')'
    ipObject.fetch()
    print ipObject.dump()
