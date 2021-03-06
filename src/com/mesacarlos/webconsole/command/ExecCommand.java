package com.mesacarlos.webconsole.command;

import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.java_websocket.WebSocket;

import com.mesacarlos.webconsole.websockets.WSServer;

public class ExecCommand implements WSCommand {

	@Override
	public void execute(WSServer wsServer, WebSocket conn, String command) {

		Bukkit.getLogger().info("[WebConsole] " + conn.getRemoteSocketAddress() + " executed '" + command + "'");
		ConsoleCommandSender sender = Bukkit.getServer().getConsoleSender();

		try {
			@SuppressWarnings("unused")
			boolean success = Bukkit.getScheduler()
					.callSyncMethod(wsServer.getMainClass(), () -> Bukkit.dispatchCommand(sender, command)).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}