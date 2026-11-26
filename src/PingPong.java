import mpi.*;

public class PingPong {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        if (size != 2) {
            if (rank == 0) {
                System.out.println("Error: Run with 2 processes.");
            }
            MPI.Finalize();
            return;
        }

        MPI.Finalize();
    }
}