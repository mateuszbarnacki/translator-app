import React, {useEffect, useMemo, useState} from "react";
import axios from "../../lib/axios";
import BaseTable from "../BaseTable";


const LanguagesTable = () => {
  const [languages, setLanguages] = useState([])
  const fetchLanguages = () => {
    axios.get(`/languages/`)
      .then(res => {
        if (res.status === 200) {
          setLanguages(res.data)
        }
      })
      .catch((error) => {
        if (error.response) {
          console.error(error.response.data);
        }
      });
  }

  const columns = useMemo(
    () => [
      {
        Header: 'Languages',
        columns: [
          {
            Header: 'Id',
            accessor: 'id',
          },
          {
            Header: 'Name',
            accessor: 'language',
          },
        ],
      },
    ],
    []
  )

  useEffect(() => {
    fetchLanguages();
  }, []);

  return (
    <BaseTable columns={columns} data={languages}></BaseTable>
  );
};

export default LanguagesTable;