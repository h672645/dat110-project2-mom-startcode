package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		// TODO - start

		// create a client object and use it to
		Client cl= new Client ("Sensor", Common.BROKERHOST, Common.BROKERPORT);

		// - connect to the broker - user "sensor" as the user name
			cl.connect();

		// - publish the temperature(s)
		for(int i=0; i<COUNT; i++){
			String msg= Integer.toString(sn.read());
			cl.publish(Common.TEMPTOPIC,msg);

			try{
				Thread.sleep(2500);
			}catch(InterruptedException e){

			}
		}
		// - disconnect from the broker

		// TODO - end
		cl.disconnect();
		
		System.out.println("Temperature device stopping ... ");

		//throw new UnsupportedOperationException(TODO.method());

	}
}
