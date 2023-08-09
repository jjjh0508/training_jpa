package com.jihwan.springdata.menu.repository;

import com.jihwan.springdata.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository  extends JpaRepository<Menu,Integer> {

    Menu findById(int menuCode);
}
