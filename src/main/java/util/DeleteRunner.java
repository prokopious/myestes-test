package util;

import java.io.File;
import java.util.Objects;



public class DeleteRunner {

	@SuppressWarnings("FieldCanBeLocal")
	private boolean deleted;

	private int daysFromCurrentDate = 1;

	public boolean isDeleted() {

		return deleted;

	}

	public void deleteFiles(File dir) {

		long purgeTime = System.currentTimeMillis() - (daysFromCurrentDate * 8 * 60 * 60 * 1000);
		if (dir.exists()) {
			if (!dir.isDirectory() && dir.lastModified() < purgeTime) {
				deleted = dir.delete();
			} else if (dir.isDirectory() && dir.lastModified() < purgeTime) {
				recursiveDelete(dir);

			} else if (dir.isDirectory()) {

				File[] files = dir.listFiles();
				if (files != null) {
					for (File aFile : files) {
						deleteFiles(aFile);
					}
				}
			}
		}

	}

	private void recursiveDelete(File file) {

		if (!file.exists()) {
			return;
		}
		if (file.isDirectory()) {
			for (File f : Objects.requireNonNull(file.listFiles())) {
				recursiveDelete(f);
			}
		}

	}

}
