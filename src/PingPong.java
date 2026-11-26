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

        int[] counter = {0};

        if (rank == 0) {
            while (counter[0] < 10) {
                MPI.COMM_WORLD.Send(counter, 0, 1, MPI.INT, 1, 0);
                MPI.COMM_WORLD.Recv(counter, 0, 1, MPI.INT, 1, 1);
                System.out.println("Rank 0 received: " + counter[0]);
            }
        } else if (rank == 1) {
        }

        MPI.Finalize();
    }
}