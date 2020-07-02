package com.mb.test.model.factory.absfactory;

public class SendSmsFactory implements Provider {
	
	@Override
	public Sender produce() {
		return new SmsSender();
	}
	
}
