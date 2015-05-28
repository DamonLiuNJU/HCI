package dataservice;

import java.rmi.Remote;

import source.ServerReplyMessage;

/*
 * 此接口只用于创建对象的方便，无实际作用
 */
public interface DataService extends Remote , ServerReplyMessage{

}
