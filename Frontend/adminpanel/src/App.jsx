import { Routes, Route } from "react-router-dom";
import AddFood from "./pages/AddFood/AddFood";
import ListFood from "./pages/ListFood/ListFood";
import Orders from "./pages/Orders/Orders";
import SideBar from "./components/Sidebar/SideBar";
import MenuBar from "./components/MenuBar/MenuBar";
import { useState } from "react";
import { ToastContainer} from 'react-toastify';

const App = () => {
  const [SideBarVisible,setSideBarVisible] =  useState(true);

  const toggleSideBar = () => {
    setSideBarVisible(!SideBarVisible);
  }
  return (
    <div className="d-flex" id="wrapper">
      <SideBar SideBarVisible={SideBarVisible}/>
      <div id="page-content-wrapper">
        <MenuBar toggleSideBar={toggleSideBar}/>
        <ToastContainer />
        <div className="container-fluid">
          <Routes>
            <Route path="/add" element={<AddFood />} />
            <Route path="/list" element={<ListFood />} />
            <Route path="/orders" element={<Orders />} />
            <Route path="/" element={<ListFood />} />
          </Routes>
        </div>
      </div>
    </div>
  );
};

export default App;