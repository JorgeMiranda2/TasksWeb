import React, { useEffect } from 'react';
import { useState } from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import AccountCircle from '@mui/icons-material/AccountCircle';
import Switch from '@mui/material/Switch';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormGroup from '@mui/material/FormGroup';
import MenuItem from '@mui/material/MenuItem';
import Menu from '@mui/material/Menu';
import { Button, Link } from '@mui/material';
import AdbIcon from '@mui/icons-material/Adb';
import { useDispatch,useSelector } from 'react-redux';
import {login,logout} from '../../redux/AuthSlice';

const NavBar = () => {
  const dispatch = useDispatch();
  const {auth, username, token} = useSelector(state => state.auth);

    const [anchorEl, setAnchorEl] = useState(null);


    const handleLogout = () =>{
     dispatch(logout())
    }
  
    const handleMenu = (event) => {
      setAnchorEl(event.currentTarget);
    };
  
    const handleClose = () => {
      setAnchorEl(null);
    };
   
        return ( <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
              <Toolbar>
              <AdbIcon sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }} />
          <Typography
            variant="h6"
            noWrap
            component="a"
            href="/"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
              flexGrow: 1
            }}
          >
                WebTasks
          </Typography>
               
                {!auth ? 
                <Box>
                    
                    <Button color="inherit" href="/login">Login</Button> 
                    <Button color="inherit" href="/register">Register</Button>
                </Box>
                :
                <div>
                <IconButton
                  size="large"
                  aria-label="account of current userName"
                  aria-controls="menu-appbar"
                  aria-haspopup="true"
                  onClick={handleMenu}
                  color="inherit"
                >
                  <Typography>{username}</Typography>
                  <AccountCircle />
                </IconButton>
                <Menu
                  id="menu-appbar"
                  anchorEl={anchorEl}
                  anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  open={Boolean(anchorEl)}
                  onClose={handleClose}
                >
                  <MenuItem onClick={handleClose}>Profile</MenuItem>
                  <MenuItem onClick={handleClose}>My account</MenuItem>
                  <MenuItem onClick={handleLogout}>Logout</MenuItem>
                </Menu>
              </div>
                 }
                
              </Toolbar>
            </AppBar>
          </Box>
        );




}
 
export default NavBar;