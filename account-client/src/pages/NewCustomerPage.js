import { Component } from "react";
import {
    InputGroup,
    InputGroupAddon,
    InputGroupText,
    Input,
    Button
} from "reactstrap";

export default class NewCustomerPage extends Component {
    render() {
        return (
            <div className="new-customer-form">
                <div style={{ margin: "64px", width: "25%" }}>
                    <InputGroup >
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Name</InputGroupText>
                        </InputGroupAddon>
                        <Input />
                    </InputGroup>
                    <br />
                    <InputGroup >
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Surname</InputGroupText>
                        </InputGroupAddon>
                        <Input />
                    </InputGroup>
                    <br />
                    {/* <InputGroup >
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Initial Credit</InputGroupText>
                        </InputGroupAddon>
                        <Input type="number" />
                    </InputGroup>
                    <br /> */}
                    <Button style={{ float: "right" }} color="primary">Create</Button>{' '}
                </div>
            </div>
        );
    }
}