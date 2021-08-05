package appMain.jpa.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import appMain.jpa.entity.City;
import appMain.jpa.repository.CityInterface;

@Service
public class CityService {

	@Autowired
	private CityInterface cityInterface;

	public String get_CitySort() {
		Iterable<City> list_asc = cityInterface.findAll(Sort.by("cityid").ascending());
		Iterable<City> list_desc = cityInterface.findAll(Sort.by("cityid").descending());
		List<String> asc = it_toList(list_asc);
		List<String> desc = it_toList(list_desc);
		return asc.toString() + " vs " + desc.toString();
	}

	public String get_CityPage(int pageNum ,int pageSize , Sort sort) {
		Sort defsort = sort;
		if(null == defsort) {
			defsort =  Sort.by("cityid").ascending();
		}
		  Pageable pageable = create_byPage(pageNum,pageSize,defsort);
		  Iterable<City> list_asc = cityInterface.findAll(pageable);
		  List<String> asc = it_toList(list_asc);
		  return asc.toString();
	}

	private List<String> it_toList(Iterable<City> list) {
		Iterator<City> li = list.iterator();
		List<String> cy = new ArrayList();
		while (li.hasNext()) {
			City city = li.next();
			int a = city.getCityid();
			String b = city.getCity_name();
			int c = city.getCity_pincode();
			cy.add(a + ":" + b + ":" + c);
		}
		return cy;
	}

	private Pageable create_byPage(int pageNumber, int pageSize, Sort sort) {
		Pageable page;
		if (null != sort) {
			page = PageRequest.of(pageNumber, pageSize);
		} else {
			page = PageRequest.of(pageNumber, pageSize, sort);
		}

		return page;
	}
}
