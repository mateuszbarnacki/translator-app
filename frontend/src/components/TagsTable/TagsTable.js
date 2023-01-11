import React, {useEffect, useMemo, useState} from "react";
import axios from "../../lib/axios";
import BaseTable from "../BaseTable";


const TagsTable = () => {
  const [tags, setTags] = useState([])
  const fetchTags = () => {

    axios.get(`/tags/`)
      .then(res => {
        if (res.status === 200) {
          setTags(res.data)
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
        Header: 'Tags',
        columns: [
          {
            Header: 'Id',
            accessor: 'id',
          },
          {
            Header: 'Name',
            accessor: 'tag',
          },
        ],
      },
    ],
    []
  )

  useEffect(() => {
    fetchTags();
  }, []);

  return (
    <BaseTable columns={columns} data={tags}></BaseTable>
  );
};

export default TagsTable;