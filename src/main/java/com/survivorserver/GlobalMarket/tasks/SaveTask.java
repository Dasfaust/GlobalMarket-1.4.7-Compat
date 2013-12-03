package com.survivorserver.GlobalMarket.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Logger;

import org.bukkit.scheduler.BukkitRunnable;

import com.survivorserver.GlobalMarket.ConfigHandler;

public class SaveTask extends BukkitRunnable {

	ConfigHandler config;
	Logger log;
	
	public SaveTask(Logger log, ConfigHandler config) {
		this.log = log;
		this.config = config;
	}
	
	@Override
	public void run() {
		File currentFile = null;
		if (config.canSave()) {
			try {
				currentFile = config.getListingsFile();
				Writer out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(currentFile)));
				out.write(config.getListingsYML().saveToString());
				out.close();		
				
				currentFile = config.getMailFile();
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(currentFile)));
				out.write(config.getMailYML().saveToString());
				out.close();
				
				currentFile = config.getHistoryFile();
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(currentFile)));
				out.write(config.getHistoryYML().saveToString());
				out.close();
				
				currentFile = config.getQueueFile();
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(currentFile)));
				out.write(config.getQueueYML().saveToString());
				out.close();
			} catch(Exception e) {
				log.severe("Could not save "
						+ currentFile.getName() + ":");
				e.printStackTrace();
			}
		} else {
			log.severe("Can't save Market data! Was it loaded correctly?");
		}
	}
}
