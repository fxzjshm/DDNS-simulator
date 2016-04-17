# DDNS-simulator
This program is under [Apache Licence][1].

----------
### Client
You need to download a client java package from [here][2].
#### Initialization
 1. Make sure you have installed Java. If not, [here][3] it is.
 2. Run: `java -jar DDNS-simulator.jar create`
 3. You can see some output, just like:

> {"IP":"123.45.67.89", "HostName":"YourHostName"}  
> {"objectId":"5713698d71cfe4005b31ab44","createdAt":"2016-04-17T10:46:37.062Z"}

The mess after "objectId" is your object id. Please remember it, or write it into a file.

#### Later use
To update your object id, you can run:

    java -jar DDNS-simulator.jar your_object_id
For example:

    java -jar DDNS-simulator.jar 5713698d71cfe4005b31ab44
If you don't trust the connection you can run it with `-c` or `--check`:

    java -jar DDNS-simulator.jar your_object_id -c
You will see:

> {"IP":"123.45.67.89", "HostName":"your_host_name"}  
{"updatedAt":"2016-04-17T11:04:35.619Z","objectId":"your_object_id"} 
{"IP":"123.45.67.89","HostName":"your_host_name","createdAt":"2016-03-26T15:49:40.709Z","updatedAt":"2016-04-17T11:04:35.619Z","objectId":"your_object_id"} 
Success.

It works normally.

**But** if you see:

> {"IP":"123.45.67.89", "HostName":"your_host_name"}  
{"updatedAt":"2016-04-17T11:04:35.619Z","objectId":"your_object_id"} 
{"IP":"87.65.43.21","HostName":"host_name_of_yours","createdAt":"2016-03-26T15:49:40.709Z","updatedAt":"2016-04-17T11:04:35.619Z","objectId":"your_object_id"} 
Something went wrong.

Then you may check your Internet connection and try again.
If it always goes wrong, please [report a bug][4].

----------
### Server
To show your current IP in your page, you can add a frame.
Usage (In your static page):

    <frame src="http://fxzjshm.github.io/DDNS-simulator/DDNS-simulator-server.html?objectId=[your_object_id]" />


  [1]: http://www.apache.org/licenses/LICENSE-2.0
  [2]: https://github.com/fxzjshm/DDNS-simulator/releases/
  [3]: http://java.com/
  [4]: https://github.com/fxzjshm/DDNS-simulator/issues
