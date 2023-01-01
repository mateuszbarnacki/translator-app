import {render, screen, within} from '@testing-library/react';

import {default as BaseTable} from './BaseTable'

const columns = [
  {
    Header: 'Test',
    columns: [
      {
        Header: 'Id',
        accessor: 'id',
      },
      {
        Header: 'Name',
        accessor: 'name'
      },
    ],
  },
];

describe("displayTable", () => {
  it.each([
    [[
      {
        id: 1,
        name: "test1",
      },
      {
        id: 2,
        name: "test2",
      }
    ]],
    [[]],
  ])('should render table', (data) => {
    render(<BaseTable data={data} columns={columns}/>);

    const table = screen.getByRole('table');
    const tbody = within(table).getByRole('tbody')
    const rows = within(tbody).queryAllByRole('row');

    expect(table).toBeInTheDocument();
    expect(rows).toHaveLength(data.length);
  });
});