package com.mb.test.model.factory.factory;

public class MailSender implements Sender {

	@Override
	public void Send() {
		System.out.println("this is mailsender!");
	}

}
