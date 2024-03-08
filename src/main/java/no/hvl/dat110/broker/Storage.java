package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	// data structure for managing subscriptions
	// maps from a topic to set of subscribed users
	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	
	// data structure for managing currently connected clients
	// maps from user to corresponding client session object
	
	protected ConcurrentHashMap<String, ClientSession> clients;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	// get the session object for a given user
	// session object can be used to send a message to the user
	
	public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);

		return session;
	}

	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	public void addClientSession(String user, Connection connection) {

		//: add corresponding client session to the storage
		// See ClientSession class
		
		ClientSession cls= new ClientSession(user,connection);
		clients.put(user,cls);

	//	throw new UnsupportedOperationException(TODO.method());
		
	}




	public void removeClientSession(String user) {

		//  disconnet the client (user) 
		// and remove client session for user from the storage
		ClientSession cls=getSession(user);
		cls.disconnect();
		clients.remove(user);

	//	throw new UnsupportedOperationException(TODO.method());
		
	}




	public void createTopic(String topic) {

		//  create topic in the storage

		subscriptions.put(topic,ConcurrentHashMap.newKeySet());

	//	throw new UnsupportedOperationException(TODO.method());
	
	}



	public void deleteTopic(String topic) {

		//  delete topic from the storage

		subscriptions.remove(topic);

	//	throw new UnsupportedOperationException(TODO.method());
		
	}



	public void addSubscriber(String user, String topic) {

		//  add the user as subscriber to the topic
	
		if(getTopics().contains(topic)){
			Set subscribers= getSubscribers(topic);
			subscribers.add(user);
			subscriptions.put(topic,subscribers);
		}else{
			System.err.println("topic eksisterer ikke!!!!!");
		}
	//	throw new UnsupportedOperationException(TODO.method());
		
	}




	public void removeSubscriber(String user, String topic) {

		//  remove the user as subscriber to the topic
		if (getTopics().contains(topic)){
			Set subscribers= getSubscribers(topic);
			subscribers.remove(user);
		
		}else{
			System.err.println("Topc eksisterer ikke!!!!!!!!!!");
		}



	//	throw new UnsupportedOperationException(TODO.method());
	}
}
