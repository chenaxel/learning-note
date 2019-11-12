package com.axel.quartz.first;

import lombok.Getter;
import lombok.Setter;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * filter file of exact extension
 *
 * @author chenzhaohui
 * @date 2019/11/8
 */
public class FileExtensionFilter extends FileFilter {

	@Setter@Getter
	private String extension;

	public FileExtensionFilter(String extension) {
		this.extension = extension;
	}

	@Override
	public boolean accept(File f) {
		String filename = f.getName().toLowerCase();
		return f.isFile() && (filename.indexOf(extension) > 0);
	}

	@Override
	public String getDescription() {
		return "filter file of exact extension";
	}

	static class Tester {
		public static void main(String[] args) {
			File t = new File("D:\\learn.temp\\dhy-reply.txt");
			System.out.println(new FileExtensionFilter("mp3").accept(t));
		}
	}
}
