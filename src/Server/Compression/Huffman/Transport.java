package src.Server.Compression.Huffman;
import java.io.Serializable;
import java.util.Objects;

public class Transport implements Serializable{
    private Character[] message;
    private HuffmanCompress compressor;


    public Transport() {
    }

    public Transport(Character[] message, HuffmanCompress compressor) {
        this.message = message;
        this.compressor = compressor;
    }

    public Character[] getMessage() {
        return this.message;
    }

    public void setMessage(Character[] message) {
        this.message = message;
    }

    public HuffmanCompress getCompressor() {
        return this.compressor;
    }

    public void setCompressor(HuffmanCompress compressor) {
        this.compressor = compressor;
    }

    public Transport message(Character[] message) {
        setMessage(message);
        return this;
    }

    public Transport compressor(HuffmanCompress compressor) {
        setCompressor(compressor);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Transport)) {
            return false;
        }
        Transport transport = (Transport) o;
        return Objects.equals(message, transport.message) && Objects.equals(compressor, transport.compressor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, compressor);
    }

    @Override
    public String toString() {
        return "{" +
            " message='" + getMessage() + "'" +
            ", compressor='" + getCompressor() + "'" +
            "}";
    }
    
}
