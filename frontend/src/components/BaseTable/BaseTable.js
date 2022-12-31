import React from "react";
import {useTable} from "react-table";
import {TableBody, TableCell, TableHead, TableRow, Table, Typography} from "@mui/material";


const BaseTable = ({columns, data}) => {
  // Use the state and functions returned from useTable to build your UI
  const {
    getTableProps,
    headerGroups,
    rows,
    prepareRow,
  } = useTable({
    columns,
    data,
  })
  // Render the UI for your table
  return (
    <Table {...getTableProps()}>
      <TableHead>
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
      <TableBody>
        {rows.map((row, i) => {
          prepareRow(row)
          return (
            <TableRow {...row.getRowProps()}>
              {row.cells.map(cell => {
                return (
                  <TableCell  align={"center"}{...cell.getCellProps()}>
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