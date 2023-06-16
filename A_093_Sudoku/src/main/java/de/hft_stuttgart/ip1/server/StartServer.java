package de.hft_stuttgart.ip1.server;

import de.hft_stuttgart.ip1.common.Start;

import java.rmi.RemoteException;

public class StartServer implements Start {


    @Override
    public void start() throws RemoteException {

        System.out.println("Server is on");
    }
}
//Klasse implementiert Interface Start (wird gerade dafür benützt den server zu starten)