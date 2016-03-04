import threading
import socket
import sys
import pickle
import queue

# lock to serialize console output
lock = threading.Lock()

class A:
    a="asdasd"

def peerlisten():
    print("Peer listen")
    server_socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    server_socket.bind(('127.0.0.1',1000))
    server_socket.listen(1)

    while True:
        print ("Waiting for commands")
        (client_socket, client_address) = server_socket.accept()
        t = threading.Thread(target=peerhandler,args=(client_socket,))
        t.start()
        t.join()

    server_socket.close()

def peerhandler(client_socket=None):
    client_data = client_socket.recv(1024)
    a = pickle.loads(client_data)
    print ("GOT COMMAND FROM "+ a.a)
    b = A()
    client_socket.send(pickle.dumps(b, -1))
    client_socket.shutdown(2)
    client_socket.close()

def connectpeer():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("127.0.0.1", 1000))
    a = A()
    s.send(pickle.dumps(a, -1))

    srv_data = s.recv(1024)
    b = pickle.loads(srv_data)
    print(b.a)
    s.close()

t = threading.Thread(target=peerlisten)
t.start()
t = threading.Thread(target=connectpeer)
t.start()
