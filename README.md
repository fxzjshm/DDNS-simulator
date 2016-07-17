# DDNS-simulator 

[![Join the chat at https://gitter.im/fxzjshm/DDNS-simulator](https://badges.gitter.im/fxzjshm/DDNS-simulator.svg)](https://gitter.im/fxzjshm/DDNS-simulator?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
This program is under [Apache Licence][1].

----------
### Client
You need to download a client java package from [the releases][2]. Now both Java and Python version are avaliable.
#### Initialization 
##### Java version
 1. Make sure you have installed Java. If not, Visit [java.com][3].
 2. Run: 
`java -jar DDNS-simulator.jar create`

 3. You can see some output, just like:

> {"IP":"123.45.67.89", "HostName":"YourHostName"}  
> {"objectId":"5713698d71cfe4005b31ab44","createdAt":"2016-04-17T10:46:37.062Z"}

The mess after "objectId" is your object id. Please remember it, for example, write it into a file.
##### Python version
 1. Make sure **Python 2 (Sorry, but not 3)** have installed. If not, visit [python.org][6].
 2. Visit [the directory][5] and download files in it.
 3. Run:
`python ddns-simulator-client.py create`
 4. Next step is the same as above, in 'Java version' Step 3.

#### Later use
To update your object id, you can run:  
(Java code)

    java -jar DDNS-simulator.jar your_object_id
(Python code)  

    python ddns-simulator-client.py your_object_id
For example:

    java -jar DDNS-simulator.jar 5713698d71cfe4005b31ab44

    python ddns-simulator-client.py 5713698d71cfe4005b31ab44
If you don't trust the connection you can run it with `-c` or `--check`:

    java -jar DDNS-simulator.jar your_object_id -c

    python ddns-simulator-client.py your_object_id -c
You will see:

> {"IP":"123.45.67.89", "HostName":"your_host_name"}  
{"updatedAt":"2016-04-17T11:04:35.619Z","objectId":"your_object_id"} 

>{"IP":"123.45.67.89","HostName":"your_host_name","createdAt":"2016-03-26T15:49:40.709Z","updatedAt":"2016-04-17T11:04:35.619Z","objectId":"your_object_id"}   

>Success.

It works normally.

**But** if you see:

> {"IP":"123.45.67.89", "HostName":"your_host_name"}  
{"updatedAt":"2016-04-17T11:04:35.619Z","objectId":"your_object_id"} 

>{"IP":"87.65.43.21","HostName":"host_name_of_yours","createdAt":"2016-03-26T15:49:40.709Z","updatedAt":"2016-04-17T11:04:35.619Z","objectId":"your_object_id"}     

>Something went wrong.

Then you may check your Internet connection and try again.
If it always goes wrong, please [report a bug][4].

----------
### Server
To show your current IP in your page, you can add a frame.
Usage (In your static page):

    <iframe src="http://fxzjshm.github.io/DDNS-simulator/DDNS-simulator-server.html?objectId=[your_object_id]" />

(Example #1)  
[http://fxzjshm.github.io/blog/minecraft-server-1-9-on-raspberry-pi/]

  [1]: http://www.apache.org/licenses/LICENSE-2.0
  [2]: https://github.com/fxzjshm/DDNS-simulator/releases/
  [3]: http://java.com/
  [4]: https://github.com/fxzjshm/DDNS-simulator/issues
  [5]: https://github.com/fxzjshm/DDNS-simulator/tree/master/client/python
  [6]: https://python.org/
  