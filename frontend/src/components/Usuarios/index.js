
import React, { useEffect, useState } from 'react';
import './Usuarios.css';
import {
    Table,
    Button,
    Modal,
    Input,
    Row,
    Col,
    Tag,
    Form,
    DatePicker,
    Select
} from 'antd';

import api from '../../services/api';

function Usuarios() {

    const [isModalVisible, setIsModalVisible] = useState(false);

    const [allUsuarios, setAllUsuarios] = useState([]);
    const [allPerfis, setAllPerfis] = useState([]);
    const [allCargos, setAllCargos] = useState([]);

    const columns = [
        {
            title: 'Nome',
            dataIndex: 'nome',
            key: 'nome',
        },
        {
            title: 'CPF',
            dataIndex: 'cpf',
            key: 'cpf',
        },
        {
            title: 'Data de Nascimento',
            dataIndex: 'data_nascimento',
            key: 'data_nascimento',
        },
        {
            title: 'Sexo',
            dataIndex: 'sexo',
            key: 'sexo',
            render: s => (
                <p>
                    {(s.includes("F") || s.includes("M")) ? s : "Outro"}
                </p>
            ),
        },
        {
            title: 'Cargo',
            key: 'cargo',
            dataIndex: 'cargo',
            render: cargo => (
                <Tag key={cargo?.id ?? 0}>
                    {cargo?.nome ?? "sem cargo"}
                </Tag>
            ),
        },
        // {
        //     title: 'Perfis',
        //     key: 'perfils',
        //     dataIndex: 'perfils',
        //     render: perfils => (
        //         <>
        //             {perfils && perfils.map(perfil => {
        //                 let color = perfil.length % 2 == 0 ? 'geekblue' : 'green';

        //                 return (
        //                     <Tag color={color} key={perfil.key}>
        //                         {perfil.nome}
        //                     </Tag>
        //                 );
        //             })}
        //         </>
        //     ),
        // },
        // {
        //     title: 'Data de Cadastro',
        //     dataIndex: 'data_cadastro',
        //     key: 'data_cadastro',
        // }
    ];

    const showModal = async () => {
        setIsModalVisible(true);

        await buscarPerfis();
        await buscarCargos();
    };

    const handleCancel = () => {
        setIsModalVisible(false);
    };

    //API
    async function buscarUsuarios() {
        try {
            const { data: usuarios } = await api.get('/usuario');

            if (usuarios) {

                for (let u of usuarios) {

                    let uPerfilsTemp = Object.assign([], u.perfils);

                    if (uPerfilsTemp) {
                        u.perfils = [];
                        let temp = [];

                        for (let idPerfil of uPerfilsTemp) {
                            const { data: perfil } = await api.get('/perfil/' + idPerfil);
                            temp.push(perfil);
                        }

                        u.perfils = temp;
                    }
                }

                setAllUsuarios(usuarios);
            }

            console.log("usuarios", usuarios);
        } catch (error) {
            console.log("Ops ..", error);
        }
    }

    async function buscarPerfis() {
        try {
            const { data: perfis } = await api.get('/perfil');

            if (perfis) {
                setAllPerfis(perfis);
            }
        } catch (error) {
            console.log("Ops ..", error);
        }
    }

    async function buscarCargos() {
        try {
            const { data: cargos } = await api.get('/cargo/?ordem=false');

            if (cargos) {
                setAllCargos(cargos);
            }
        } catch (error) {
            console.log("Ops ..", error);
        }
    }

    async function adicionarUsuario(values) {
        try {

            //JSON SERVER
            let ultimaKey = allUsuarios.slice(-1).pop();

            let usuario = {
                id: ultimaKey.id + 1,
                key: ultimaKey.key + 1,
                ...values,
                data_nascimento: values['data_nascimento'].format('DD-MM-YYYY'),
                data_cadastro: new Date().toLocaleDateString("pt-BR"),
                cargo: {
                    id: values['cargoId']
                }
            };

            await api.post('/usuario', usuario);

            console.log("adicionarUsuario:", usuario);

            await buscarUsuarios();

            setIsModalVisible(false);

        } catch (error) {
            console.log("Ops ..", error);
        }
    }

    const onFinishFailed = (errorInfo) => {
        console.log("Failed:", errorInfo);
    };

    useEffect(() => {
        buscarUsuarios();
    }, []);

    return (
        <div>
            <Row justify="end">
                <Col span={6}>
                    <Button type="primary" onClick={showModal}>
                        Criar usuario
                    </Button>
                </Col>
            </Row>

            <Modal
                title="Criar usuario"
                visible={isModalVisible}
                onCancel={handleCancel}
                footer={null}
            >
                <Form
                    layout="vertical"
                    name="usuarioForm"
                    onFinish={adicionarUsuario}
                    onFinishFailed={onFinishFailed}
                >
                    <Form.Item
                        name="nome"
                        label="Nome do usuário"
                        rules={[
                            {
                                required: true,
                            },
                        ]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        name="cpf"
                        label="CPF"
                        rules={[
                            {
                                required: true,
                            },
                        ]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        name="data_nascimento"
                        label="Data de Nascimento"
                        rules={[
                            {
                                required: true,
                            },
                        ]}
                    >
                        <DatePicker />
                    </Form.Item>

                    <Form.Item
                        name="sexo"
                        label="Sexo (M) ou (F)"
                        rules={[
                            {
                                required: true,
                            },
                        ]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        name="cargoId"
                        label="Adicione um cargo"
                        rules={[
                            {
                                required: true,
                            },
                        ]}
                    >
                        <Select>
                            {
                                allCargos.map(c => (
                                    <Select.Option key={c.id} value={c.id}>{c.nome}</Select.Option>
                                ))
                            }
                        </Select>
                    </Form.Item>

                    <Form.Item
                        name="perfils"
                        label="Selecione os Perfis"
                        rules={[
                            {
                                required: true,
                                message: 'Por favor selecione um perfil',
                                type: 'array',
                            },
                        ]}
                    >
                        <Select mode="multiple" placeholder="Por favor selecione um perfil">
                            {
                                allPerfis.map(p => (
                                    <Select.Option key={p.id} value={p.id}>{p.nome}</Select.Option>
                                ))
                            }
                        </Select>
                    </Form.Item>

                    <Form.Item
                        style={{ paddingTop: 30 }}
                        wrapperCol={{
                            xs: {
                                span: 24,
                                offset: 0,
                            },
                            sm: {
                                span: 16,
                                offset: 8,
                            },
                        }}
                    >
                        <Button type="primary" htmlType="submit">
                            Salvar usuário
                    </Button>
                    </Form.Item>

                </Form>
            </Modal>

            <br />

            {allUsuarios.length > 0 && <Table
                dataSource={allUsuarios}
                columns={columns} />}

        </div>
    );
}

export default Usuarios;
