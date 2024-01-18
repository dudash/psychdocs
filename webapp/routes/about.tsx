import * as React from 'react';
import Link from '@mui/joy/Link';
import Typography from '@mui/joy/Typography';
import Button from '@mui/joy/Button';

export default function About() {
  return (
    <React.Fragment>
      <Typography component="h1" gutterBottom>
        Material UI Remix in TypeScript example
      </Typography>
      <Link href="/dashboard" color="primary">
        Go to the main page
      </Link>
    </React.Fragment>
  );
}