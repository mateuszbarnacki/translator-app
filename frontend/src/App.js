import './App.css';
import {LanguagesTable, MessagesTable, TagsTable} from "./components";
import {Grid} from "@mui/material";


function App() {


  return (
    <Grid
      container
      direction="column"
      justifyContent="space-evenly"
      alignItems="center"
      spacing={4}
      paddingTop={5}
    >
      <Grid item>
        <TagsTable/>
      </Grid>
      <Grid item>
        <LanguagesTable/>
      </Grid>
      <Grid item>
        <MessagesTable/>
      </Grid>
    </Grid>
  );
}

export default App;
