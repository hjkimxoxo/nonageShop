package nonageShop.service;

import java.util.ArrayList;

import nonageShop.dao.impl.AddressDaoImpl;
import nonageShop.dto.Address;

public class AddressService {
	public AddressDaoImpl dao = AddressDaoImpl.getInstance();
	
	public ArrayList<Address> selectAddressByDong(String dong){
		return dao.selectAddressByDong(dong);
	}
}
