## Readme

### Root context is
*/api*

### For view employees
*/api/employees*

### For view orders
*/api/orderses*

### For view furnitures
*/api/furnitures*

### For add new employee
**POST** to */api/employees*

And when, for add association with department needs

**PUT*** to */api/employees/{id}/department
with *Content-type:text/uri-list* and uri with department