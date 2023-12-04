package src.Test;

import java.nio.charset.Charset;

import src.Server.Compression.Huffman.HuffmanCompress;
import src.entity.Driver;
import src.entity.Vehicle;

public class TestCompressCar {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("ABC5G12", "12345678900", new Driver("joao", "00000000000"), "Subaru", 2012);
        HuffmanCompress compressor = new HuffmanCompress();
        
        Charset charset = Charset.forName("UTF-8");
        byte[] bytes = vehicle.toString().getBytes(charset);
        
        System.out.println("Tamanho da string descomprimid " + bytes.length);
        Character[] compressedCar = compressor.compress(vehicle.toString()); 
        System.out.println("Tamanho da string comprimida " + compressedCar.toString().length());
        Vehicle vehicleConstructor = new Vehicle();
        //vehicleConstructor = vehicleConstructor.createVehicleFromString(compressor.decompress(compressedCar));
        System.out.println(vehicle.stringToVehicle(vehicle.toString()));
    }
}