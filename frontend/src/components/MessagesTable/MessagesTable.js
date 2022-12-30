import React, {useEffect, useMemo, useState} from "react";
import axios from "../../lib/axios";
import BaseTable from "../BaseTable";

const MessagesTable = () => {
  const [messages, setMessages] = useState([])
  const fetchMessages = () => {
    axios.get(`/api/messages/`)
      .then(res => {
        if (res.status === 200) {
          setMessages(res.data)
        }
      })
      .catch((error) => {
        if (error.response) {
          console.error(error.response.data); // => the response payload
        }
      });
  }

  const parseTags = ({value}) => {
    let allTagsNames = value.reduce((accumulator, tag) => accumulator += `${tag.tag}, `, '')
    return allTagsNames.slice(0, -2)
  }

  const columns = useMemo(
    () => [
      {
        Header: 'Messages',
        columns: [
          {
            Header: 'Id',
            accessor: 'id',
          },
          {
            Header: 'Original Message Id',
            accessor: 'original_message',
            Cell: ({value}) => value ? String(value.language) : 'This is original message'
          },
          {
            Header: 'Content',
            accessor: 'content',
          },
          {
            Header: 'Language',
            accessor: 'language.language',
          },
          {
            Header: 'Tags',
            accessor: 'tags',
            Cell: value => parseTags(value)
          },
        ],
      },
    ],
    []
  )

  useEffect(() => {
    fetchMessages();
  }, []);

  return (
    <BaseTable columns={columns} data={messages}></BaseTable>
  );
};

export default MessagesTable;