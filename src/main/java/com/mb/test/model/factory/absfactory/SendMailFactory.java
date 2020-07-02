package com.mb.test.model.factory.absfactory;

public class SendMailFactory implements Provider {

	@Override
	public Sender produce() {
		return new MailSender();
	}

}
