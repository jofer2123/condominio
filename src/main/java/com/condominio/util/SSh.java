package com.condominio.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSh {
	private Constantes cons=new Constantes();

    private Session session;
 
    private void connect(String username, String password, String host, int port)
            throws JSchException, IllegalAccessException {
            if (this.session == null || !this.session.isConnected()) {
                JSch jsch = new JSch();
     
                this.session = jsch.getSession(username, host, port);
                this.session.setPassword(password);
     
                // Parametro para no validar key de conexion.
                this.session.setConfig("StrictHostKeyChecking", "no");
     
                this.session.connect();
            } else {
                throw new IllegalAccessException("Sesion SSH ya iniciada.");
            }
        }
    
    private List<String> executeCommand(String command)
            throws IllegalAccessException, JSchException, IOException {
    	List<String> list=new ArrayList<>();
            if (this.session != null && this.session.isConnected()) {

                ChannelExec channelExec = (ChannelExec) this.session.
                    openChannel("exec");
                InputStream in = channelExec.getInputStream();            
                channelExec.setCommand(command);
                channelExec.connect(); 
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                
                String linea;
                while ((linea = reader.readLine()) != null) {
                    list.add(linea);
                }
     
                channelExec.disconnect();
               
                return list;
            } else {
                throw new IllegalAccessException("No existe sesion SSH iniciada.");
            }
        }
     
        private final void disconnect() {
            this.session.disconnect();
        }
public List<String> comandoLinux(String comando) {
	List<String> list=new ArrayList<>();
  try {
	connect(cons.USER, cons.PASS, cons.HOST, 22);
} catch (IllegalAccessException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (JSchException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  
  try {
	list.addAll(executeCommand(comando));
} catch (IllegalAccessException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (JSchException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    disconnect();
    return list;
}
    
}
