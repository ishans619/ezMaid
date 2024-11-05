import AdminRowItem from "./AdminRowItem"

function AdminTable(props) {

    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    <th scope='col'>#</th>
                    <th scope='col'>User Name</th>
                    <th scope='col'>First Name</th>
                    <th scope='col'>Middle Name</th>
                    <th scope='col'>Last Name</th>
                    <th scope='col'>Contact Number</th>
                    <th scope='col'>Address</th>
                    <th scope='col'>Email</th>
                    <th scope='col'>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    props.admins.map((admin, index) => (
                        <AdminRowItem
                            key={index}
                            count={++index}
                            username={admin.user.username}
                            adminId={admin.adminId}
                            fName={admin.fName}
                            mName={admin.mName}
                            lName={admin.lName}
                            contactNumber={admin.contactNumber}
                            address={admin.address}
                            email={admin.email}
                            isActive={admin.user.isActive}
                            handleGetAdmins={props.handleGetAdmins}
                        />
                    ))
                }
            </tbody>
        </table>
    )
}

export default AdminTable