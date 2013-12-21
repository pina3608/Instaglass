package com.google.devezek.instaglass.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

/**
 * 
 * Nota: Para usar esta clase hay que cambiar los permisos:
 * <uses-permission android:name="android.permission.GET_ACCOUNTS"></uses-permission>
 */

public class GoogleID {
	static String acc = null;
	
	/**
	 * 
	 * @param Contexto que se puede sacar de Application
	 * @return El nombre de la cuenta, o anon@anon.an si no hay ninguna cuenta de google.
	 */
	static String getMainAccount(Context c) {
		if (acc != null)
			return acc;
		AccountManager manager = (AccountManager) c.getSystemService(Context.ACCOUNT_SERVICE);
		Account[] list = manager.getAccounts();
		for (Account a : list) {
			if (a.type.equals("com.google"))
				acc = a.name;
		}
		return acc = "anon@anon.an"; 
	}
	
}
