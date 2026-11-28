import mpi.*;
public class ScatterGatherDoubler {
	private static final int UNIT_SIZE = 4;
	private static final int ROOT = 0;
	public static void main(String[] args) throws MPIException {
		MPI.Init(args);
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();
		if (size != 4) {
			if (rank == ROOT) {
            System.err.println("This program requires exactly 4 processes (got " + size + ").");
			}
			MPI.Finalize();
			return;
        }
		int totalElements = UNIT_SIZE * size; // 4 * 4 = 16 integers
		int[] sendBuffer = new int[rank == ROOT ? totalElements : 0];
		int[] recvChunk = new int[UNIT_SIZE];
		int[] gatheredBuffer = new int[rank == ROOT ? totalElements : 0];
		if (rank == ROOT) {
            for (int i = 0; i < totalElements; i++) {
                sendBuffer[i] = i + 1; // [1, 2, ..., 16]
            }
            System.out.println("Process " + rank + " (Root) initial array: " + Arrays.toString(sendBuffer));
        }
		MPI.COMM_WORLD.Scatter(
	            sendBuffer, 0, UNIT_SIZE, MPI.INT,
	            recvChunk,  0, UNIT_SIZE, MPI.INT,
	            ROOT
	        );
		System.out.println("Process " + rank + " received chunk: " + Arrays.toString(recvChunk));
		for (int i = 0; i < UNIT_SIZE; i++) {
            recvChunk[i] = recvChunk[i] * 2;
        }
		System.out.println("Process " + rank + " doubled chunk:  " + Arrays.toString(recvChunk));
    }

}
