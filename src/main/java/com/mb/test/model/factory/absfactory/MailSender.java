package com.mb.test.model.factory.absfactory;

public class MailSender implements Sender {

	@Override
	public void Send() {
		System.out.println("this is mailsender!");
	}

}