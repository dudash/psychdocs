import * as React from 'react';
import { CssVarsProvider } from '@mui/joy/styles';
import CssBaseline from '@mui/joy/CssBaseline';
import Box from '@mui/joy/Box';
import Sidebar from './components/Sidebar';
import Header from './components/Header';
import AppChatView from './components/AppChatView';

export default function App() {
  return (
    <CssVarsProvider disableTransitionOnChange>
      <CssBaseline />
      <Box sx={{ display: 'flex', minHeight: '100dvh' }}>
        <Sidebar />
        <Header />
        <Box component="main" className="MainContent" 
          sx={{flex: 1, overflow: 'visible'}}
        >
          <AppChatView />
        </Box>
      </Box>
    </CssVarsProvider>
  );
}