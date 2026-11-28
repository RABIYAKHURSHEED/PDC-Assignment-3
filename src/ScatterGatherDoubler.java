import mpi.*;
public class ScatterGatherDoubler {
	private static final int UNIT_SIZE = 4;
	private static final int ROOT = 0;
	public static void main(String[] args) throws MPIException {
		MPI.Init(args);
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();
		if (size != 4) {
        }
		if (rank == ROOT) {
            System.err.println("This program requires exactly 4 processes (got " + size + ").");
        }
    }

}
