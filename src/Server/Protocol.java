package src.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import src.Server.Compression.Huffman.HuffmanCompress;
import src.Server.Compression.Huffman.Transport;
import src.entity.Vehicle;


public interface Protocol extends Remote {
    public void init()throws RemoteException;
    public void insert(Character[] vehicle, HuffmanCompress tree) throws RemoteException;
    public Transport remove(Character[] vehicle, HuffmanCompress tree) throws RemoteException;
    public List<Transport> listAll() throws RemoteException;
    public Transport search(Character[] renavam, HuffmanCompress tree) throws RemoteException;
    public Transport searchByPlate(Character[] plate, HuffmanCompress tree) throws RemoteException;
    public void update(Character[] newVehicle,HuffmanCompress tree) throws RemoteException;
    public Transport size() throws RemoteException;
}
