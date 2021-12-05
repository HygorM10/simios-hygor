package br.com.hygor.simios.adapters.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class MatrixSequenceUtil {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public int getAllSequences(String[][] matrix, int qtdMaxLettersSequence)
			throws InterruptedException, ExecutionException {

		CompletableFuture<Integer> horizontalSequenceFuture = CompletableFuture
				.supplyAsync(() -> this.getHorizontalSequence(matrix, qtdMaxLettersSequence));

		CompletableFuture<Integer> verticalSequenceFuture = CompletableFuture
				.supplyAsync(() -> this.getVerticalSequence(matrix, qtdMaxLettersSequence));

		CompletableFuture<Integer> mainDiagonalSequenceFuture = CompletableFuture
				.supplyAsync(() -> this.getMainDiagonalSequence(matrix, qtdMaxLettersSequence));

		CompletableFuture<Integer> secondaryDiagonalSequenceFuture = CompletableFuture
				.supplyAsync(() -> this.getSecondaryDiagonalSequence(matrix, qtdMaxLettersSequence));

		while (!(horizontalSequenceFuture.isDone() && verticalSequenceFuture.isDone()
				&& mainDiagonalSequenceFuture.isDone() && secondaryDiagonalSequenceFuture.isDone())) {

			String h = (horizontalSequenceFuture.isDone()) ? "is done" : "is not done";
			log.info("horizontalSequenceFuture {}", h);

			String v = (verticalSequenceFuture.isDone()) ? "is done" : "is not done";
			log.info("verticalSequenceFuture {}", v);

			String m = (mainDiagonalSequenceFuture.isDone()) ? "is done" : "is not done";
			log.info("mainDiagonalSequenceFuture {}", m);

			String s = (secondaryDiagonalSequenceFuture.isDone()) ? "is done" : "is not done";
			log.info("secondaryDiagonalSequenceFuture {}", s);

			Thread.sleep(100);
		}

		return horizontalSequenceFuture.get() + verticalSequenceFuture.get() + mainDiagonalSequenceFuture.get()
				+ secondaryDiagonalSequenceFuture.get();

	}

	private int getVerticalSequence(String[][] matrix, int qtdMaxLettersSequence) {

		int countEqualLetters = 1;
		int countSequence = 0;
		String lastLetter = "";
		String currentLetter;

		for (int i = 0; i < matrix.length; i++) {
			for (int k = 0; k < matrix[i].length; k++) {
				if (k > 0) {
					currentLetter = matrix[i][k];
					if (lastLetter.equals(currentLetter)) {
						countEqualLetters++;
					} else {
						countEqualLetters = 1;
					}
				} else {
					currentLetter = matrix[i][k];
				}
				lastLetter = currentLetter;
				if (countEqualLetters == qtdMaxLettersSequence) {
					countSequence++;
				}
			}
			countEqualLetters = 1;
		}

		log.info("Vertical: " + countSequence);
		return countSequence;

	}

	private int getHorizontalSequence(String[][] matrix, int qtdMaxLettersSequence) {

		int countEqualLetters = 1;
		int countSequence = 0;
		String lastLetter = "";
		String currentLetter;

		for (int i = 0; i < matrix.length; i++) {
			for (int k = 0; k < matrix[i].length; k++) {
				if (k > 0) {
					currentLetter = matrix[k][i];
					if (lastLetter.equals(currentLetter)) {
						countEqualLetters++;
					} else {
						countEqualLetters = 1;
					}
				} else {
					currentLetter = matrix[k][i];
				}
				lastLetter = currentLetter;
				if (countEqualLetters == qtdMaxLettersSequence) {
					countSequence++;
				}
			}
			countEqualLetters = 1;
		}

		log.info("Horinzontal: " + countSequence);
		return countSequence;

	}

	private int getMainDiagonalSequence(String[][] matrix, int qtdMaxLettersSequence) {

		int countEqualLetters = 1;
		int countSequence = 0;
		String lastLetter = "";
		String currentLetter;

		for (int i = 0; i < matrix.length; ++i) {

			for (int j = 0; j < matrix.length - i; ++j) {
				currentLetter = matrix[j][j + i];
				if (lastLetter.equals(currentLetter)) {
					countEqualLetters++;
				}

				if (countEqualLetters == qtdMaxLettersSequence) {
					countSequence++;
					countEqualLetters = 1;
				}
				lastLetter = currentLetter;
			}

			countEqualLetters = 1;

			if (i != 0) {

				lastLetter = "";

				for (int j = 0; j < matrix.length - i; ++j) {
					currentLetter = matrix[j + i][j];
					if (lastLetter.equals(currentLetter)) {
						countEqualLetters++;
					}

					if (countEqualLetters == qtdMaxLettersSequence) {
						countSequence++;
						countEqualLetters = 1;
					}
					lastLetter = currentLetter;
				}

				countEqualLetters = 1;

			}

		}

		log.info("Diagnal: " + countSequence);
		return countSequence;

	}

	private int getSecondaryDiagonalSequence(String[][] matrix, int qtdMaxLettersSequence) {

		int countEqualLetters = 1;
		int countSequence = 0;
		String lastLetter = "";
		String currentLetter = "";

		for (int c = 1; c <= matrix.length; c++) {
			for (int i = 0; i < matrix.length; i++) {
				for (int k = 0; k < (matrix.length); k++) {
					if (i + k == (matrix.length - c)) {
						currentLetter = matrix[i][k];

						if (lastLetter.equals(currentLetter)) {
							countEqualLetters++;
						}

						if (countEqualLetters == qtdMaxLettersSequence) {
							countSequence++;
							countEqualLetters = 1;
						}
						lastLetter = currentLetter;

					}
				}
			}

			countEqualLetters = 1;

			for (int i = 0; i < matrix.length; i++) {
				for (int k = 0; k < (matrix.length); k++) {
					if (i + k == (matrix.length + (c - 1))) {

						currentLetter = matrix[i][k];

						if (lastLetter.equals(currentLetter)) {
							countEqualLetters++;
						}

						if (countEqualLetters == qtdMaxLettersSequence) {
							countSequence++;
							countEqualLetters = 1;
						}
						lastLetter = currentLetter;

					}
				}
			}

			log.info("Segunda Diagonal: " + countSequence);
			countEqualLetters = 1;

		}

		return countSequence;

	}

}
