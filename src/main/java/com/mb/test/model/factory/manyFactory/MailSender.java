package com.mb.test.model.factory.manyFactory;

public class MailSender implements Sender {

	@Override
	public void Send() {
		System.out.println("this is mailsender!");
	}

}
