# Demonstration of  2 apps communicating via proxy and stub without AIDL
IPC between 2 apps with just stub and proxy provided by android.os.binder between server and client .
### Purpose of these apps 
To demonstrate that AIDL is really not required to do IPC between 2 apps , if we can use the system generated IxxxService.java 
containing stub and proxy in both server and client it is possiable to do direct functions calls.
AIDL files is used by AIDL tool to generate the IxxxService.java files containing the stub and proxy with onTransact and transact methods .

These IxxxService.java can be distrubuted as raw file or can be wrapped in .jar to client .
#### Note: we need to maintain the backward compatibility . 


### Terminologies  
(copied from https://events.static.linuxfound.org/images/stories/slides/abs2013_gargentas.pdf)
### Binder (Framework)
The overall IPC architecture
### Binder Driver
The kernel-level driver that fascinates the communication across process boundaries
### Binder Protocol
Low-level protocol (ioctl-based) used to communicate with the Binder driver
### IBinder Interface
A well-defined behavior (i.e. methods) that Binder Objects must implement
### AIDL
Android Interface Definition Language used to describe business operations on an IBinder Interface
### Binder (Object)
A generic implementation of the IBinder interface
### Binder Token
An abstract 32-bit integer value that uniquely identifies a Binder object across all processes on the system
### Binder Service
An actual implementation of the Binder (Object) that implements the business operations
### Binder Client
An object wanting to make use of the behavior offered by a binder service
### Binder Transaction
An act of invoking an operation (i.e. a method) on a remote Binder object, which may involve sending/receiving data, over the Binder Protocol
Parcel "Container for a message (data and object references) that can be sent through an IBinder." A unit of
transactional data - one for the outbound request, and another for the inbound reply
### Marshalling
A procedure for converting higher level applications data structures (i.e. request/response parameters) into
parcels for the purposes of embedding them into Binder transactions
### Unmarshalling
A procedure for reconstructing higher-level application data-structures (i.e. request/response parameters)
from parcels received through Binder transactions
### Proxy
An implementation of the AIDL interface that un/marshals data and maps method calls to transactions
submitted via a wrapped IBinder reference to the Binder object
### Stub
A partial implementation of the AIDL interface that maps transactions to Binder Service method calls while un/marshalling data
### Context Manager (a.k.a. servicemanager)
A special Binder Object with a known handle (registered as handle 0) that is used as a registry/lookup service for other Binder Objects (name → handle mapping)
Binder Communication a

### meaning of in,out tags in AIDL file 
#### - in: 
    The client passes the parameters to the server for use. 
    Primitives are in by default, and cannot be otherwise.
    This is default mode.
#### - out: 
    The client passes a parameter to the server, which serves as a container, discards all the attribute values, 
    fills in the content, and then returns it to the client for further processing.
#### - inout: 
    The client passes the parameters to the server, and the server can use the values of the parameters. 
    At the same time, the client can modify the parameters. If it is a collection array, it can modify its internal sub-objects. 
### Meaning of Oneway:
        By default, the RPC calls from the client are synchronous.  Will block untill it returns . 
        So better to make RPC  calls in worker thread than main or ui thread.
        So oneway keyword is used for making the call Asynchronous . 
        Caution : Asynchronous methods must not have out and inout arguments. 
        They must also return void.
##### ClassNotFoundException when bundle used with parcellable  
        If your aidl interface includes methods that accept Bundle as arguments which is expected to contain parcelables, 
        please ensure you set the classloader of the Bundle by calling Bundle.setClassLoader(ClassLoader) before attempting to read from the Bundle. 
        Otherwise, you will run into ClassNotFoundException even though the parcelable is correctly defined in your application. 
        
        EX: before reading from bundle passed via aidl : call bundle.setClassLoader(getClass().getClassLoader());

### Which thread does AIDL calls comes at the reciver end on in the server process :
        Calls from a remote process are dispatched from a thread pool the platform maintains inside of your own process.
        We must be prepared for incoming calls from unknown threads, with multiple calls happening at the same time. 
        In other words, an implementation of an AIDL interface must be completely thread-safe. 
        Calls made from one thread on same remote object arrive in order on the receiver end.
