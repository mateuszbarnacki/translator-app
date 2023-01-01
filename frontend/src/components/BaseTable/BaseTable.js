import React from "react";
import {useTable} from "react-table";
import {TableBody, TableCell, TableHead, TableRow, Table, Typography} from "@mui/material";


const BaseTable = ({columns, data}) => {
  const {
    getTableProps,
    headerGroups,
    rows,
    prepareRow,
  } = useTable({
    columns,
    data,
  })
  return (
    <Table {...getTableProps()}>
      <TableHead role={"thead"}>
        {headerGroups.map(headerGroup => (
          <TableRow {...headerGroup.getHeaderGroupProps()}>
            {headerGroup.headers.map(column => (
              <TableCell align={"center"} {...column.getHeaderProps()}>

                <Typography sx={{fontWeight: 'bold'}}>{column.render('Header')}</Typography>
              </TableCell>
            ))}
          </TableRow>
        ))}
      </TableHead>
      <TableBody role={"tbody"}>
        {rows.map((row, i) => {
          prepareRow(row)
          return (
            <TableRow {...row.getRowProps()}>
              {row.cells.map(cell => {
                return (
                  <TableCell  align={"center"} {...cell.getCellProps()}>
                    {cell.render('Cell')}
                  </TableCell>
                )
              })}
            </TableRow>
          )
        })}
      </TableBody>
    </Table>
  )
}
export default BaseTable;