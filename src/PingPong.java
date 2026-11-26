import mpi.*;

public class PingPong {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        MPI.Finalize();
    }
}