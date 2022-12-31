import './App.css';
import {LanguagesTable, MessagesTable, TagsTable} from "./components";
import {Grid, Typography} from "@mui/material";


function App() {


  return (
    <Grid
      container
      direction="column"
      justifyContent="center"
      rowSpacing={4}
      alignItems="center"
    >
      <Grid item>
          <Typography variant="h2">
            Cisco Translator App
          </Typography>
      </Grid>
      <Grid item>
        <MessagesTable/>
      </Grid>
      <Grid item>
        <TagsTable/>
      </Grid>
      <Grid item>
        <LanguagesTable/>
      </Grid>
    </Grid>
  );
}

export default App;
